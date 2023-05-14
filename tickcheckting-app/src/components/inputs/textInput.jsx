import React from 'react';

const Input = ({
  id,
  label,
  className,
  classNameDiv,
  classNameLabel,
  placeholder,
  register,
  error,
  type = 'text',
  defaultValue = '',
}) => {
  return (
    <>
      <div className={`${classNameDiv}`}>
        <div className={`mb-2 ${classNameLabel}`}>
          {label}
        </div>
        <div className='mb-2 relative flex-grow'>
          <div className='flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none'>
          </div>
          <input
            type={type}
            defaultValue={defaultValue}
            id={id}
            placeholder={placeholder}
            className={className}
            {...register}
          />
        </div>
      </div>
      {error && <p className='text-sm text-red-700 mt-1'>{error}</p>}
    </>
  );
};

export default Input;